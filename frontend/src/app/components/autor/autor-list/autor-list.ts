import {Component} from '@angular/core';
import {AutorService} from '../autor.service';
import {RouterModule} from '@angular/router';
import {Autor} from '../autor';
import {Pagination} from '../../pagination/pagination';
import {Page} from '../../../shared/models/page';

@Component({
    selector: 'autor-list',
    imports: [RouterModule, Pagination],
    templateUrl: './autor-list.html',
    styleUrl: './autor-list.scss',
    standalone: true,
    providers: [AutorService]
})
export class AutorList {

    dataSource: Autor[] = [];
    page!: Page<Autor>;

    constructor(private autorService: AutorService) {
    }

    ngOnInit(): void {
        this.autorService.list().subscribe(data => {
            this.page = data;
            this.dataSource = data.content;
        });
    }

    trocarPagina(pagina: number): void {
        this.autorService.list(pagina)
            .subscribe(response => {
                this.page = response;
                this.dataSource = response.content;
            });
    }
}
