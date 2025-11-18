import { Injectable } from '@angular/core';
import {RestService} from '../../shared/services/rest.service';
import {Autor} from './autor';
import { Observable, take } from 'rxjs';

@Injectable()
export class AutorService extends RestService<Autor> {

    constructor() {
        super("autores", (autor: Autor) => autor.codAu!);
    }

    findByNome(nome: string): Observable<Autor[]> {
        return this._http.get<Autor[]>(`${this._apiUrl}/search`, {
            params: {
                nome: nome
            }
        }).pipe(take(1));
    }

}
