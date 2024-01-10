import { Product } from "src/app/products/types/Product.interface";

export interface OrderItemResponse{
    id: Product,
    quantity: number
}