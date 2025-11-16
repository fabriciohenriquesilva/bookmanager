import {HttpClient} from '@angular/common/http';
import {Inject, inject, Injectable} from '@angular/core';
import {Observable, take} from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class RestService<T extends {getId(): number}> {

    private _http = inject(HttpClient);
    private _apiUrl: string;

    constructor(
        @Inject(String) protected _endpoint: string
    ) {
        this._apiUrl = `api/${this._endpoint}`;
    }

    list(): Observable<any> {
        return this._http.get(this._apiUrl).pipe(take(1));
    }

    getById(id: number | string) {
        const url = `${this._apiUrl}/${id}`;
        return this._http.get<T>(url).pipe(take(1));
    }

    save(entity: T): Observable<any> {
        if (entity.getId()) {
            return this.update(entity);
        }
        return this.create(entity);
    }

    remove(id: number) {
        return this._http.delete(`${this._apiUrl}/${id}`).pipe(take(1));
    }

    private create(entity: T) {
        return this._http.post(this._apiUrl, entity).pipe(take(1));
    }

    private update(entity: T) {
        return this._http.put(this._apiUrl, entity).pipe(take(1));
    }
}
