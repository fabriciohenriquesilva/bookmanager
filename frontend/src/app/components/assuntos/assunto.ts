export class Assunto {
    constructor(public codAs?: number,
                public descricao?: string) {
    }

    getId(): number {
        return this.codAs ?? 0;
    }
}
