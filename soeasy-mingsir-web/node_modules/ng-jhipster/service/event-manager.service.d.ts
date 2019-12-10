import { Observable, Observer, Subscription } from 'rxjs';
/**
 * An utility class to manage RX events
 */
export declare class JhiEventManager {
    observable: Observable<any>;
    observer: Observer<any>;
    constructor();
    /**
     * Method to broadcast the event to observer
     */
    broadcast(event: any): void;
    /**
     * Method to subscribe to an event with callback
     */
    subscribe(eventName: any, callback: any): Subscription;
    /**
     * Method to unsubscribe the subscription
     */
    destroy(subscriber: Subscription): void;
}
