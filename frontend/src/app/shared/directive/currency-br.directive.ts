import {
    Directive,
    HostListener,
    ElementRef,
    forwardRef
} from '@angular/core';
import {
    ControlValueAccessor,
    NG_VALUE_ACCESSOR
} from '@angular/forms';

@Directive({
    selector: '[currencyBr]',
    standalone: true,
    providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => CurrencyBrDirective),
            multi: true
        }
    ]
})
export class CurrencyBrDirective implements ControlValueAccessor {

    private onChange = (value: any) => {};
    private onTouched = () => {};

    constructor(private el: ElementRef<HTMLInputElement>) {}

    @HostListener('input', ['$event'])
    onInput(event: any) {
        let value = event.target.value;

        // Remove tudo que não é número
        value = value.replace(/\D/g, '');

        if (!value) {
            this.updateValue('0,00');
            return;
        }

        // Converte string para centavos REALMENTE
        const number = (parseInt(value, 10) / 100).toFixed(2);

        const formatted = new Intl.NumberFormat('pt-BR', {
            minimumFractionDigits: 2,
            maximumFractionDigits: 2
        }).format(parseFloat(number));

        this.updateValue(formatted);
    }

    private updateValue(formatted: string) {
        this.el.nativeElement.value = formatted;
        this.onChange(this.toNumber(formatted));
    }

    private toNumber(masked: string): number {
        return Number(masked.replace(/\./g, '').replace(',', '.'));
    }

    writeValue(value: any): void {
        if (value === null || value === undefined) {
            this.el.nativeElement.value = '0,00';
            return;
        }

        const formatted = new Intl.NumberFormat('pt-BR', {
            minimumFractionDigits: 2,
            maximumFractionDigits: 2
        }).format(value);

        this.el.nativeElement.value = formatted;
    }

    registerOnChange(fn: any): void {
        this.onChange = fn;
    }

    registerOnTouched(fn: any): void {
        this.onTouched = fn;
    }
}
