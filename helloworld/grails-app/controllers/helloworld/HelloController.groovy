package helloworld

import groovyx.net.http.HTTPBuilder

import static groovyx.net.http.ContentType.URLENC
import static groovyx.net.http.Method.POST
import grails.converters.JSON

class HelloController {

    def index() {

//        render "Hello World!"
        def jsonReq = request.JSON
        def name = jsonReq.name
        def jsonStr = jsonReq.name
        def result

        def paramValue = '{"actId":"","bactId":"","busMobile":"13591159520","categoryId":"","channelId":"1003","custId":"NF5066235","delivery":[],"empId":"105136","exFamoney":"","exFapri":"","hquantity":"","orderFlag":"Y","orderInfos":"109526&1&170&100","orgId":"415","posId":"41211","quantity":"","summer":[{"actId":109526,"packageSize":1,"vividList":[{"categoryId":170,"quantityBottle":0,"quantityBox":100,"type":1}]}]}'
        def tokenValue = MD5Utils.encode(paramValue, MD5Utils.MD5_KEY)

        def http = new HTTPBuilder('http://vendtest.nfsq.com.cn:8888')
        http.request(POST, URLENC) {
            req ->
                headers.Accept = 'application/json'
                uri.path = '/nfsq_xs/terminal/promo/order.json'
                body = [
                        token  : tokenValue,
                        param  : paramValue,
                        version: '197'
                ]
                response.success = { resp, json ->
                    println json
                    result = json
                }
        }
        render result as JSON
    }
}
