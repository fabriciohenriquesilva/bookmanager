import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Page} from '../../shared/models/page';

@Component({
    selector: 'pagination',
    imports: [],
    standalone: true,
    templateUrl: './pagination.html',
    styleUrl: './pagination.scss',
})
export class Pagination<T> implements OnInit {

    @Input()
    page!: Page<T>;

    @Output() eventEmitter: EventEmitter<number> = new EventEmitter<number>();

    constructor() {
    }

    ngOnInit(): void {
    }

    trocarPagina(pagina: number) {
        this.eventEmitter.emit(pagina);
    }
}
