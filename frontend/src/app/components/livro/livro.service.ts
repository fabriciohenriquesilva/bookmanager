import {Injectable} from '@angular/core';
import {RestService} from '../../shared/services/rest.service';
import {Livro} from './livro';
import {Autor} from '../autor/autor';
import {Observable, take} from 'rxjs';
import {Assunto} from '../assunto/assunto';

@Injectable()
export class LivroService extends RestService<Livro> {

    constructor() {
        super("livros", (livro: Livro) => livro.codL!);
    }

    getAutoresByLivro(codL: number): Observable<Autor[]> {
        return this._http.get<Autor[]>(`${this._apiUrl}/${codL}/autores`).pipe(take(1));
    }

    getAssuntosByLivro(codL: number): Observable<Assunto[]> {
        return this._http.get<Assunto[]>(`${this._apiUrl}/${codL}/assuntos`).pipe(take(1));
    }

    findByTitulo(titulo: string): Observable<Livro[]> {
        return this._http.get<Livro[]>(`${this._apiUrl}/search`, {
            params: {
                titulo: titulo
            }
        }).pipe(take(1));
    }
}
