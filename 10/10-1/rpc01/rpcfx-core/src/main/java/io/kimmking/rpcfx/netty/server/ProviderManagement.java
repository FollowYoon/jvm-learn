package io.kimmking.rpcfx.netty.server;

import com.google.common.base.Joiner;
import io.kimmking.rpcfx.annotation.Provider;
import io.kimmking.rpcfx.api.RpcfxRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * @author qindong
 * @date 2021/11/29 15:55
 */
@Slf4j
public class ProviderManagement {

    /**
     * 用map保存service对应的不同分组版本实例
     * service:group:version --> class
     */
    private static Map<String, Object> proxyMap = new HashMap<>();

    /**
     * init方法，把对应包名下所有带@Provider注解的服务添加到map中
     * @param basePackageName
     */
    public static void init(String basePackageName) throws Exception{
        Class[] classes = getClasses(basePackageName);
        for (Class c: classes) {
            Provider annotation = (Provider) c.getAnnotation(Provider.class);
            if (annotation == null) {
                continue;
            }
            String group = annotation.group();
            String version = annotation.version();
            String provider = Joiner.on(":").join(annotation.serviceName(), group, version);
            proxyMap.put(provider, c.newInstance());
            log.info("load provider class: " + annotation.serviceName() + ":" + group + ":" + version + " :: " + c.getName());
        }
    }

    /**
     * 获取RpcRequest中对应的class实例
     * @param request
     * @return
     */
    public static Object getProviderService(RpcfxRequest request) {
        String group = "default";
        String version= "default";
        String className = request.getServiceClass();
        if (request.getGroup() != null) {
            group = request.getGroup();
        }
        if (request.getVersion() != null) {
            version = request.getVersion();
        }
        return proxyMap.get(Joiner.on(":").join(className, group, version));
    }

    /**
     * 查找包名下所有的class
     * @param packageName
     * @return
     * @throws ClassNotFoundException
     * @throws IOException
     */
    private static Class[] getClasses(String packageName) throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes.toArray(new Class[0]);
    }

    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        assert files != null;
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }

}
