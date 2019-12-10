import { EventEmitter } from '@angular/core';
import { FaIconComponent } from '@fortawesome/angular-fontawesome';
export declare class JhiSortDirective {
    predicate: string;
    ascending: boolean;
    callback: Function;
    predicateChange: EventEmitter<any>;
    ascendingChange: EventEmitter<any>;
    activeIconComponent: FaIconComponent;
    constructor();
    sort(field: any): void;
}
