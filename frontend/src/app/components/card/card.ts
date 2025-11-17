import {Component, Input} from '@angular/core';
import {RouterModule} from '@angular/router';

@Component({
    selector: 'card',
    standalone: true,
    imports: [RouterModule],
    templateUrl: './card.html',
    styleUrl: './card.scss',
})
export class Card {

    @Input({required: true})
    icon: string = '';

    @Input({required: true})
    title: string = '';

    @Input({required: true})
    info: string = '';

    @Input({required: true})
    route: string = '';

}
