import {AssuntoList} from './components/assunto/assunto-list/assunto-list';
import {Home} from './components/home/home';
import {AssuntoForm} from './components/assunto/assunto-form/assunto-form';
import {AutorList} from './components/autor/autor-list/autor-list';
import {AutorForm} from './components/autor/autor-form/autor-form';
import {LivroList} from './components/livro/livro-list/livro-list';
import {LivroForm} from './components/livro/livro-form/livro-form';

export const routes: { path: string; component: any }[] = [
    {
        path: '',
        component: Home
    },
    {
        path: 'assuntos',
        component: AssuntoList
    },
    {
        path: 'assuntos/edit/:id',
        component: AssuntoForm
    },
    {
        path: 'assuntos/create',
        component: AssuntoForm
    },
    {
        path: 'autores',
        component: AutorList
    },
    {
        path: 'autores/edit/:id',
        component: AutorForm
    },
    {
        path: 'autores/create',
        component: AutorForm
    },
    {
        path: 'livros',
        component: LivroList
    },
    {
        path: 'livros/edit/:id',
        component: LivroForm
    },
    {
        path: 'livros/create',
        component: LivroForm
    },
];
