export class Livro {
    constructor(public codL?: number,
                public titulo?: string,
                public editora?: string,
                public edicao?: number,
                public anoPublicacao?: string,
                public valor?: number,
                public assuntosId?: number[],
                public autoresId?: number[]) {
    }

    // assuntosId: number[] | null = null;

    // autoresId: number[] | null = null;
}
