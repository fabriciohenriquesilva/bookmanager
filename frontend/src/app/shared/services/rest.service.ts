import {HttpClient, HttpParams} from '@angular/common/http';
import {Inject, inject} from '@angular/core';
import {Observable, take} from 'rxjs';
import {Page} from '../models/page';

export class RestService<T> {

    protected _http = inject(HttpClient);
    protected _apiUrl: string;

    constructor(
        @Inject(String) protected _endpoint: string,
        private idResolver: IdResolver<T>
    ) {
        this._apiUrl = `api/${this._endpoint}`;
    }

    list(page: number = 0): Observable<Page<T>> {
        let params = new HttpParams();

        if (page > 0) {
            params = params.set('page', page);
        }

        return this._http.get<Page<T>>(this._apiUrl, {params}).pipe(take(1));
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
