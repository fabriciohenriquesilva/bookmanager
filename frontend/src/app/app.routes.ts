import {AssuntoList} from './components/assuntos/assunto-list/assunto-list';
import {Home} from './components/home/home';
import {AssuntoForm} from './components/assuntos/assunto-form/assunto-form';

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
