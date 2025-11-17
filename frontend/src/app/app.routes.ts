import {AssuntoList} from './components/assunto/assunto-list/assunto-list';
import {Home} from './components/home/home';
import {AssuntoForm} from './components/assunto/assunto-form/assunto-form';

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

];
