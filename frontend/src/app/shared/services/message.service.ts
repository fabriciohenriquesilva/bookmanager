import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class MessageService {

    message: string | null = null;
    type: 'success' | 'danger' | 'warning' | 'info' = 'success';

    show(message: string, type: 'success' | 'danger' | 'warning' | 'info' = 'success'): void {
        this.message = message;
        this.type = type;

        if (type !== 'danger') {
            setTimeout(() => this.clear(), 4000);
        }
    }

    clear(): void {
        this.message = null;
    }
}
