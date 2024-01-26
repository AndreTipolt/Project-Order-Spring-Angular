import { OrderResponse } from "src/app/order/types/OrderResponse.interface";
import { Adress } from "../../adress/types/Adress.interface";

export interface UserResponse{
    id: string,
    name: string,
    email: string,
    listOrders?: OrderResponse[],
    listAdress?: Adress[],
    phone?: string,
    listNotifications: Notification[]
}
