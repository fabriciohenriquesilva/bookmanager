import {Component} from '@angular/core';
import {MessageService} from '../../../shared/services/message.service';
import {Livro} from '../livro';
import {LivroService} from '../livro.service';
import {ActivatedRoute, Router, RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {CurrencyBrDirective} from '../../../shared/directive/currency-br.directive';

@Component({
    selector: 'livro-form',
    imports: [RouterModule, FormsModule, CommonModule, CurrencyBrDirective],
    templateUrl: './livro-form.html',
    styleUrl: './livro-form.scss',
    standalone: true,
    providers: [LivroService]
})
export class LivroForm {

    model: Livro = new Livro();

    constructor(private livroService: LivroService,
                private activatedRoute: ActivatedRoute,
                private messageService: MessageService,
                private router: Router) {
    }

    ngOnInit(): void {
        const id: number = this.activatedRoute.snapshot.params['id'] as number;

        if (id) {
            this.livroService.getById(id).subscribe(data => {
                this.model = data;
            });
        }
    }

    salvar(): void {
        this.livroService.save(this.model).subscribe({
            next: (livro: Livro) => {
                this.messageService.show('Registro salvo com sucesso!', 'success');
                setTimeout(() => this.router.navigate(['/livros/edit/', livro.codL]), 1500);
            },
            error: (err) => {
                console.error(err);
                const mensagem = err.error?.detail || 'Erro ao salvar o registro.';
                this.messageService.show(mensagem, 'danger');
            }
        });
    }

    excluir(): void {
        this.livroService.remove(this.model.codL!).subscribe({
            next: (data: any) => {
                this.messageService.show('Registro excluído com sucesso!', 'success');
                setTimeout(() => this.router.navigate(['/livros']), 2000)
            },
            error: (err) => {
                console.error(err);

                const mensagem = err.error?.detail || 'Erro ao excluir o registro.';
                this.messageService.show(mensagem, 'danger');
            }
        });
    }
}
