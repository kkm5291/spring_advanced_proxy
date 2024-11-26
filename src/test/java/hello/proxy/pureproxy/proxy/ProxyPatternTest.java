package hello.proxy.pureproxy.proxy;

import hello.proxy.pureproxy.proxy.code.CacheProxy;
import hello.proxy.pureproxy.proxy.code.ProxyPatternClient;
import hello.proxy.pureproxy.proxy.code.RealSubject;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {

    /**
     * 프록시를 적용하지 않은 경우임
     * 실제 subject를 가져오기 때문에, 3초의 시간이 걸렸음
     */
    @Test
    void noProxyTest() {
        RealSubject realSubject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(realSubject);
        client.execute();
        client.execute();
        client.execute();
    }

    /**
     * 프록시를 적용한 경우임
     * 데이터가 존재하지 않을 경우에만 실제 subject를 가져왔음
     * 이후에는 캐시를 통해서 데이터를 가져왔기 때문에 1초의 시간으로 데이터를 가져올 수 있었음
     */
    @Test
    void cacheProxyTest() {
        RealSubject realSubject = new RealSubject();
        CacheProxy cacheProxy = new CacheProxy(realSubject);
        ProxyPatternClient client = new ProxyPatternClient(cacheProxy);

        client.execute();
        client.execute();
        client.execute();
    }
}
