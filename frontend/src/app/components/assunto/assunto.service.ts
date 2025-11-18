import {Injectable} from '@angular/core';
import {RestService} from '../../shared/services/rest.service';
import {Assunto} from './assunto';
import {Observable, take} from 'rxjs';
import {Livro} from '../livro/livro';

@Injectable()
export class AssuntoService extends RestService<Assunto> {

    constructor() {
        super("assuntos", assunto => assunto.codAs!);
    }

    findByDescricao(descricao: string): Observable<Assunto[]> {
        return this._http.get<Assunto[]>(`${this._apiUrl}/search`, {
            params: {
                descricao: descricao
            }
        }).pipe(take(1));
    }

    getLivrosByAssunto(codAs: number): Observable<Livro[]> {
        return this._http.get<Livro[]>(`${this._apiUrl}/${codAs}/livros`).pipe(take(1));
    }
}
