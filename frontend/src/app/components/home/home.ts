import {Component} from '@angular/core';
import {Card} from '../card/card';

@Component({
    selector: 'app-home',
    standalone: true,
    imports: [Card],
    templateUrl: './home.html',
    styleUrl: './home.scss',
})
export class Home {

}
