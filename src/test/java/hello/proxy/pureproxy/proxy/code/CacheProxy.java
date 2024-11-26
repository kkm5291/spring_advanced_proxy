package hello.proxy.pureproxy.proxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheProxy implements Subject {

    // 실제객체
    private Subject target;

    // 캐시할 데이터
    private String cacheValue;

    public CacheProxy(Subject target) {
        this.target = target;
    }


    /**
     * taget은 실제 객체임
     * 즉, 만약 데이터가 존재하지 않는다면 target에서 값을 가져오게 됨 (실제 객체까지 들어감)
     * 그러나 만약 데이터가 존재하고 있는 상황이라면 target까지 가지 않고 CacheProxy에서 데이터를 가져옴
     * 즉, 실제 객체까지 가지 않고 프록시에서 데이터를 빠르게 가져올 수 있음
     * @return
     */
    @Override
    public String operation() {
        log.info("프록시 호출");
        if (cacheValue == null) {
            cacheValue = target.operation();
        }
        return cacheValue;
    }
}
