import {Component, signal} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {MessageService} from './shared/services/message.service';

@Component({
    selector: 'app-root',
    standalone: true,
    imports: [RouterOutlet],
    templateUrl: './app.html',
    styleUrl: './app.scss',
    providers: [MessageService]
})
export class App {
    protected readonly title = signal('Book Manager');

    constructor(public messageService: MessageService) {
    }
}
