import { Pageable } from "./Pageable.interface";
import { Product } from "./Product.interface";

export interface Response{
    content: Product[],
    pageable: Pageable
}