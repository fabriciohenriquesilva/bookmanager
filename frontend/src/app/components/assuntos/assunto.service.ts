import { Injectable } from '@angular/core';
import {RestService} from '../../shared/services/rest.service';
import {Assunto} from './assunto';

@Injectable()
export class AssuntoService extends RestService<Assunto> {

    constructor() {
        super("assuntos", assunto => assunto.codAs!);
    }

}
