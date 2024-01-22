const constants = {
    baseurl: "http://localhost:8080/api",
    authHeader : {
        headers: {
            Authorization: 'Basic ' + btoa('alice:user1Pass'),
        },
    }
}
export default constants