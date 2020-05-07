package caucho.hession;


import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.HessianOutput;
import com.zzw.serialize.Serializer;

/**
 * @Date 2020/4/26
 * @Author zhenwei.wang
 */
public class HessianSerializer implements Serializer {

    private Hessian2Input input = new Hessian2Input();
    private HessianOutput output = new HessianOutput();

    public <T> byte[] serialize(T obj) {
        if (obj == null) {
            throw new IllegalArgumentException("obj is null");
        }

        return new byte[0];
    }

    public <T> T deserialize(byte[] data, Class<T> tClass) {
        return null;
    }
}
