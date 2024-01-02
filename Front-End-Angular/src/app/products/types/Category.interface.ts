import { Product } from "./Product.interface";

export interface Category{
    id?: number,
    name: string,
    listProducts: Product[]
}