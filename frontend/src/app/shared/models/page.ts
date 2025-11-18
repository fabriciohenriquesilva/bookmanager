export interface Page<T> {
    content: T[];
    totalElements: number;
    last: boolean;
    totalPages: number;
    size: number;
    number: number;
    numberOfElements: number;
    first: boolean;
    empty: boolean;
    pageable: Pageable;
}

interface Pageable {
    offset: number;
    pageNumber: number;
    pageSize: number;
}
