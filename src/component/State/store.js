import { applyMiddleware, combineReducers, legacy_createStore } from "redux";
import { authReducer } from "./Authentication/Reducer";
import { thunk } from "redux-thunk";
import restaurantReducer from "./Restaurant/Reducer";
import menuItemReducer from "./Menu/Reducer";
import cartReducer from "./Cart/Reducer";
import orderReducer from "./Order/Reducer";
import restaurantsOrderReducer from "./Restaurant Order/Reducer";
import ingredientReducer from "./ingredients/Reducer";

const rooteReducer=combineReducers({
    auth:authReducer,
    cart: cartReducer,
    restaurant:restaurantReducer,
    menu:menuItemReducer,
    order:orderReducer,
    restaurantOrders : restaurantsOrderReducer,
    ingredients:ingredientReducer
});

export const store = legacy_createStore(rooteReducer,applyMiddleware(thunk));
