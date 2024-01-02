export interface Pageable{
    sort: {
        sorted: boolean,
        unsorted: boolean,
        empty: boolean
    },
    offset: Number,
    pageNumber: Number,
    pageSize: Number,
    unpaged: boolean,
    paged: boolean,
    last: boolean,
    totalPages: Number,
    totalElements: Number,
    first: boolean,
    size: Number,
    number: Number,
    numberOfElements: Number,
    empty: boolean
}