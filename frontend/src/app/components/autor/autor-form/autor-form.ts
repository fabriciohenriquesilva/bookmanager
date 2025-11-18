import {Component, OnInit} from '@angular/core';
import {Autor} from '../autor';
import {MessageService} from '../../../shared/services/message.service';
import {AutorService} from '../autor.service';
import {ActivatedRoute, Router, RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {Livro} from '../../livro/livro';
import {LivroService} from '../../livro/livro.service';

@Component({
    selector: 'autor-form',
    imports: [RouterModule, FormsModule, CommonModule],
    templateUrl: './autor-form.html',
    styleUrl: './autor-form.scss',
    standalone: true,
    providers: [AutorService, LivroService]
})
export class AutorForm implements OnInit {

    model: Autor = new Autor();

    constructor(private autorService: AutorService,
                private livroService: LivroService,
                private activatedRoute: ActivatedRoute,
                private messageService: MessageService,
                private router: Router) {
    }

    ngOnInit(): void {
        const id: number = this.activatedRoute.snapshot.params['id'] as number;

        if (id) {
            this.autorService.getById(id).subscribe(data => {
                this.model = data;
            });

            this.autorService.getLivrosByAutor(id).subscribe((data: Livro[]) => {
                if (data.length > 0) {
                    this.livroList = data;
                }
            });
        }
    }

    salvar(): void {
        this.model.livrosId = this.livroList
            ?.map(livro => livro?.codL)
            ?.filter((id): id is number => id !== undefined) || [];

        this.autorService.save(this.model).subscribe({
            next: (autor: Autor) => {
                this.messageService.show('Registro salvo com sucesso!', 'success');
                setTimeout(() => this.router.navigate(['/autores/edit/', autor.codAu]), 1500);
            },
            error: (err) => {
                console.error(err);
                const mensagem = err.error?.detail || 'Erro ao salvar o registro.';
                this.messageService.show(mensagem, 'danger');
            }
        });
    }

    excluir(): void {
        this.autorService.remove(this.model.codAu!).subscribe({
            next: (data: any) => {
                this.messageService.show('Registro excluído com sucesso!', 'success');
                setTimeout(() => this.router.navigate(['/autores']), 2000)
            },
            error: (err) => {
                console.error(err);

                const mensagem = err.error?.detail || 'Erro ao excluir o registro.';
                this.messageService.show(mensagem, 'danger');
            }
        });
    }

    livroAutocompleteList: Livro[] = [];
    livroList: Livro[] = [];
    livroAutocompleteVisible: boolean = false;

    onSearchLivro(titulo: string): void {
        if (titulo.length > 0) {
            this.livroService.findByTitulo(titulo).subscribe(res => {
                this.livroAutocompleteList = res;
            });
        } else {
            this.livroAutocompleteList = [];
        }
    }

    adicionarLivro(livro: Livro): void {
        if (!this.livroList.some(a => a.codL === livro.codL)) {
            this.livroList.push(livro);
        }
        this.livroAutocompleteList = [];
    }

    removerLivro(livro: Livro): void {
        this.livroList = this.livroList
            .filter(a => a.codL !== livro.codL);
    }

}
