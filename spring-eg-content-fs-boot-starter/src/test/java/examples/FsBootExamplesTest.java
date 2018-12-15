package examples;

import com.github.paulcwarren.ginkgo4j.Ginkgo4jConfiguration;
import tests.smoke.ContentStoreTests;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.paulcwarren.ginkgo4j.Ginkgo4jSpringRunner;

@RunWith(Ginkgo4jSpringRunner.class)
@Ginkgo4jConfiguration(threads=1)
@SpringBootTest(classes = Application.class)
public class FsBootExamplesTest extends ContentStoreTests {

}
