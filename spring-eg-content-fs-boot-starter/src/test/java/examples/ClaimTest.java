package examples;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.paulcwarren.ginkgo4j.Ginkgo4jConfiguration;
import com.github.paulcwarren.ginkgo4j.Ginkgo4jSpringRunner;

import examples.AbstractSpringContentTests;

@RunWith(Ginkgo4jSpringRunner.class)
@Ginkgo4jConfiguration(threads=1)
@SpringBootTest(classes = examples.Application.class)   
public class ClaimTest extends AbstractSpringContentTests {

}
