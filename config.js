{
    "hosts": [{
    "host" : "mon-site.com:2000",
    "lb" : "roundrobin",
    "workers" : [
        "jeuxvideo.fr",
        "yahoo.fr",
        "allocine.fr"
    ],
    "headers" : {
        "incoming": {
            "add" : [
                {"My-ProxyHeader" : "123"}
            ]
        },
        "outgoing" : {
            "add" : [
                {"For-Client-Header" : "765"}
            ]
        }
    }
}]
}