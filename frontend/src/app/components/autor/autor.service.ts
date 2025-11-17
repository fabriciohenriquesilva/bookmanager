import { Injectable } from '@angular/core';
import {RestService} from '../../shared/services/rest.service';
import {Autor} from './autor';

@Injectable()
export class AutorService extends RestService<Autor> {

    constructor() {
        super("autores", (autor: Autor) => autor.codAu!);
    }

}
