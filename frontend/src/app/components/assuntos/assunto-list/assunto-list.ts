import {Component, OnInit} from '@angular/core';
import {RouterModule} from '@angular/router';
import {AssuntoService} from '../assunto.service';
import {Assunto} from '../assunto';

@Component({
    selector: 'assunto-list',
    standalone: true,
    imports: [RouterModule],
    templateUrl: './assunto-list.html',
    styleUrl: './assunto-list.scss',
    providers: [AssuntoService]
})
export class AssuntoList implements OnInit {

    assuntoList: Assunto[] = [];

    constructor(private assuntoService: AssuntoService) {
    }

    ngOnInit(): void {
        this.assuntoService.list().subscribe(data => {
            this.assuntoList = data.content;
            console.log(data)
        });
    }


}
