/**
 * An utility service for pagination
 */
export declare class JhiPaginationUtil {
    constructor();
    /**
     * Method to find whether the sort is defined
     */
    parseAscending(sort: string): boolean;
    /**
     * Method to query params are strings, and need to be parsed
     */
    parsePage(page: string): number;
    /**
     * Method to sort can be in the format `id,asc` or `id`
     */
    parsePredicate(sort: string): string;
}
