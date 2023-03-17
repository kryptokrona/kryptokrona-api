export function login (email) {
    let user = {email};
    localStorage.setItem("user", JSON.stringify(user));
    return user;
}
export function logout () {
    localStorage.removeItem("user");
    return true;
}