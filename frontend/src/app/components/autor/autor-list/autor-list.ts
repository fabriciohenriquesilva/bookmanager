import {Component} from '@angular/core';
import {AutorService} from '../autor.service';
import {RouterModule} from '@angular/router';
import {Autor} from '../autor';

@Component({
    selector: 'autor-list',
    imports: [RouterModule],
    templateUrl: './autor-list.html',
    styleUrl: './autor-list.scss',
    standalone: true,
    providers: [AutorService]
})
export class AutorList {

    dataSource: Autor[] = [];

    constructor(private autorService: AutorService) {
    }

    ngOnInit(): void {
        this.autorService.list().subscribe(data => {
            this.dataSource = data.content;
        });
    }
}
