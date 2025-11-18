import {HttpClient} from '@angular/common/http';
import {Inject, inject} from '@angular/core';
import {Observable, take} from 'rxjs';

export class RestService<T> {

    protected _http = inject(HttpClient);
    protected _apiUrl: string;

    constructor(
        @Inject(String) protected _endpoint: string,
        private idResolver: IdResolver<T>
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

    save(entity: T): Observable<T> {
        if (this.idResolver(entity) != null) {
            return this.update(entity);
        }
        return this.create(entity);
    }

    remove(id: number): Observable<any> {
        return this._http.delete(`${this._apiUrl}/${id}`).pipe(take(1));
    }

    private create(entity: T): Observable<T> {
        return this._http.post<T>(this._apiUrl, entity).pipe(take(1));
    }

    private update(entity: T): Observable<T> {
        return this._http.put<T>(this._apiUrl, entity).pipe(take(1));
    }
}

export type IdResolver<T> = (entity: T) => number;
