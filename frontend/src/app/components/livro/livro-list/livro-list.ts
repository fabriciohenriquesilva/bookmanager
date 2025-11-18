import {Component, OnInit} from '@angular/core';
import {Livro} from '../livro';
import {LivroService} from '../livro.service';
import {RouterModule} from '@angular/router';
import {DecimalPipe} from '@angular/common';

@Component({
    selector: 'livro-list',
    imports: [RouterModule, DecimalPipe],
    templateUrl: './livro-list.html',
    styleUrl: './livro-list.scss',
    standalone: true,
    providers: [LivroService]
})
export class LivroList implements OnInit {

    dataSource: Livro[] = [];

    constructor(private livroService: LivroService) {
    }

    ngOnInit(): void {
        this.livroService.list().subscribe(data => {
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
}
