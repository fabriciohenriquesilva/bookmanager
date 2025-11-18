import {Component, OnInit} from '@angular/core';
import {RouterModule} from '@angular/router';
import {AssuntoService} from '../assunto.service';
import {Assunto} from '../assunto';
import {Page} from '../../../shared/models/page';
import {Pagination} from '../../pagination/pagination';

@Component({
    selector: 'assunto-list',
    standalone: true,
    imports: [RouterModule, Pagination],
    templateUrl: './assunto-list.html',
    styleUrl: './assunto-list.scss',
    providers: [AssuntoService]
})
export class AssuntoList implements OnInit {

    dataSource: Assunto[] = [];
    page!: Page<Assunto>;

    constructor(private assuntoService: AssuntoService) {
    }

    ngOnInit(): void {
        this.assuntoService.list().subscribe(data => {
            this.page = data;
            this.dataSource = data.content;
        });
    }

    trocarPagina(pagina: number): void {
        this.assuntoService.list(pagina)
            .subscribe(response => {
                this.page = response;
                this.dataSource = response.content;
            });
    }

}
