import { Product } from "../../products/types/Product.interface";

export interface Category{
    id?: number,
    name: string,
    listProducts: Product[]
}
