import {Component, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AssuntoService} from '../assunto.service';
import {ActivatedRoute, Router, RouterModule} from '@angular/router';
import {Assunto} from '../assunto';
import {FormsModule} from '@angular/forms';
import {MessageService} from '../../../shared/services/message.service';
import {Livro} from '../../livro/livro';
import {LivroService} from '../../livro/livro.service';

@Component({
    selector: 'assunto-form',
    imports: [RouterModule, FormsModule, CommonModule],
    templateUrl: './assunto-form.html',
    styleUrl: './assunto-form.scss',
    standalone: true,
    providers: [AssuntoService, LivroService]
})
export class AssuntoForm implements OnInit {

    model: Assunto = new Assunto();

    constructor(private assuntoService: AssuntoService,
                private livroService: LivroService,
                private activatedRoute: ActivatedRoute,
                private messageService: MessageService,
                private router: Router) {
    }

    ngOnInit(): void {
        const id: number = this.activatedRoute.snapshot.params['id'] as number;

        if (id) {
            this.assuntoService.getById(id).subscribe(data => {
                this.model = data;
            });

            this.assuntoService.getLivrosByAssunto(id).subscribe((data: Livro[]) => {
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

        this.assuntoService.save(this.model).subscribe({
            next: (assunto: Assunto) => {
                this.messageService.show('Registro salvo com sucesso!', 'success');
                setTimeout(() => this.router.navigate(['/assuntos/edit/', assunto.codAs]), 1500);
            },
            error: (err) => {
                console.error(err);
                const mensagem = err.error?.detail || 'Erro ao salvar o registro.';
                this.messageService.show(mensagem, 'danger');
            }
        });
    }

    excluir(): void {
        this.assuntoService.remove(this.model.codAs!).subscribe({
                next: (data: any) => {
                    this.messageService.show('Registro excluído com sucesso!', 'success');
                    setTimeout(() => this.router.navigate(['/assuntos']), 2000)
                },
                error: (err) => {
                    console.error(err);

                    const mensagem = err.error?.detail || 'Erro ao excluir o registro.';
                    this.messageService.show(mensagem, 'danger');
                }
            })
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
