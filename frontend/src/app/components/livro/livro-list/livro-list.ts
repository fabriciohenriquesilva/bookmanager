import {Component, OnInit} from '@angular/core';
import {Livro} from '../livro';
import {LivroService} from '../livro.service';
import {RouterModule} from '@angular/router';
import {DecimalPipe} from '@angular/common';
import {Pagination} from "../../pagination/pagination";
import {Page} from '../../../shared/models/page';

@Component({
    selector: 'livro-list',
    imports: [RouterModule, DecimalPipe, Pagination],
    templateUrl: './livro-list.html',
    styleUrl: './livro-list.scss',
    standalone: true,
    providers: [LivroService]
})
export class LivroList implements OnInit {

    dataSource: Livro[] = [];
    page!: Page<Livro>;

    constructor(private livroService: LivroService) {
    }

    ngOnInit(): void {
        this.livroService.list().subscribe(data => {
            this.page = data;
            this.dataSource = data.content;
        });
    }

    relatorio(): void {
        this.livroService.relatorio().subscribe({
            next: (blob) => {
                const fileURL = URL.createObjectURL(blob);
                window.open(fileURL, '_blank');
            },
            error: (err) => {
                console.error(err);
            }
        });
    }

    trocarPagina(pagina: number): void {
        this.livroService.list(pagina)
            .subscribe(response => {
                this.page = response;
                this.dataSource = response.content;
            });
    }
}
