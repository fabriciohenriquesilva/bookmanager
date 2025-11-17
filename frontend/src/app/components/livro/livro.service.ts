import { Injectable } from '@angular/core';
import {RestService} from '../../shared/services/rest.service';
import {Livro} from './livro';

@Injectable()
export class LivroService extends RestService<Livro> {

    constructor() {
        super("livros", (livro: Livro) => livro.codL!);
    }

}
