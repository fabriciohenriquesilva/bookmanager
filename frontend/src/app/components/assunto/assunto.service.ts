import {Injectable} from '@angular/core';
import {RestService} from '../../shared/services/rest.service';
import {Assunto} from './assunto';
import {Observable, take} from 'rxjs';

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
}
