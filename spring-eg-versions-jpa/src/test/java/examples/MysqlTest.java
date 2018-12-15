package examples;

import com.github.paulcwarren.ginkgo4j.Ginkgo4jSpringRunner;
import internal.org.springframework.versions.LockingService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import tests.versioning.VersionedDocumentAndVersioningRepository;
import tests.versioning.VersioningTests;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.github.paulcwarren.ginkgo4j.Ginkgo4jDSL.FIt;

@RunWith(Ginkgo4jSpringRunner.class)
//@Ginkgo4jConfiguration(threads=1)
@ContextConfiguration(classes = { MysqlTestConfig.class })
public class MysqlTest extends VersioningTests {
//
//    @Autowired
//    private VersionedDocumentAndVersioningRepository repo;
//
//    @Autowired
//    private LockingService lockingService;
//
//    {
//        FIt("deadlock tests", () -> {
//            ExecutorService executor = Executors.newFixedThreadPool(5);
//            for (int i = 0; i < 5; i++) {
//                Runnable worker = new ThreadTest(i, repo);
//                executor.execute(worker);
//            }
//            executor.shutdown();
//            while (!executor.isTerminated()) {
//            }
//        });
//    }
}
