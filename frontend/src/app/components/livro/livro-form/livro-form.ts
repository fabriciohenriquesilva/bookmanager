import {Component} from '@angular/core';
import {MessageService} from '../../../shared/services/message.service';
import {Livro} from '../livro';
import {LivroService} from '../livro.service';
import {ActivatedRoute, Router, RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {CurrencyBrDirective} from '../../../shared/directive/currency-br.directive';
import {Autor} from '../../autor/autor';
import {AutorService} from '../../autor/autor.service';
import {AssuntoService} from '../../assunto/assunto.service';
import {Assunto} from '../../assunto/assunto';

@Component({
    selector: 'livro-form',
    imports: [RouterModule, FormsModule, CommonModule, CurrencyBrDirective],
    templateUrl: './livro-form.html',
    styleUrl: './livro-form.scss',
    standalone: true,
    providers: [LivroService, AutorService, AssuntoService]
})
export class LivroForm {

    model: Livro = new Livro();

    constructor(private livroService: LivroService,
                private autorService: AutorService,
                private assuntoService: AssuntoService,
                private activatedRoute: ActivatedRoute,
                private messageService: MessageService,
                private router: Router) {
    }

    ngOnInit(): void {
        this.autorAutocompleteList = [];
        const id: number = this.activatedRoute.snapshot.params['id'] as number;

        if (id) {
            this.livroService.getById(id).subscribe(data => {
                console.log(data)
                this.model = data;
            });

            this.livroService.getAutoresByLivro(id).subscribe((data: Autor[]) => {
                if (data.length > 0) {
                    this.autorList = data;
                }
            });

            this.livroService.getAssuntosByLivro(id).subscribe((data: Assunto[]) => {
                if (data.length > 0) {
                    this.assuntoList = data;
                }
            });
        }
    }

    salvar(): void {
        this.model.autoresId = this.autorList
            ?.map(autor => autor?.codAu)
            ?.filter((id): id is number => id !== undefined) || [];

        this.model.assuntosId = this.assuntoList
            ?.map(assunto => assunto?.codAs)
            ?.filter((id): id is number => id !== undefined) || [];

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

    assuntoAutocompleteList: Assunto[] = [];
    assuntoList: Assunto[] = [];
    assuntoAutocompleteVisible: boolean = false;

    onSearchAssunto(nome: string): void {
        if (nome.length > 0) {
            this.assuntoService.findByDescricao(nome).subscribe(res => {
                this.assuntoAutocompleteList = res;
            });
        } else {
            this.assuntoAutocompleteList = [];
        }
    }

    adicionarAssunto(autor: Assunto): void {
        if (!this.assuntoList.some(a => a.codAs === autor.codAs)) {
            this.assuntoList.push(autor);
        }
        this.assuntoAutocompleteList = [];
    }

    removerAssunto(autor: Assunto): void {
        this.assuntoList = this.assuntoList
            .filter(a => a.codAs !== autor.codAs);
    }

    autorAutocompleteList: Autor[] = [];
    autorList: Autor[] = [];
    autorAutocompleteVisible: boolean = false;

    onSearchAutor(nome: string): void {
        if (nome.length > 0) {
            this.autorService.findByNome(nome).subscribe(res => {
                this.autorAutocompleteList = res;
            });
        } else {
            this.autorAutocompleteList = [];
        }
    }

    adicionarAutor(autor: Autor): void {
        if (!this.autorList.some(a => a.codAu === autor.codAu)) {
            this.autorList.push(autor);
        }
        this.autorAutocompleteList = [];
    }

    removerAutor(autor: Autor): void {
        this.autorList = this.autorList
            .filter(a => a.codAu !== autor.codAu);
    }

}
