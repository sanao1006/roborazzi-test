package app.sanao1006.roborazziTest

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isRoot
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.test.core.app.ActivityScenario
import app.sanao1006.roborazziTest.ui.theme.RoborazzitestTheme
import com.github.takahirom.roborazzi.DEFAULT_ROBORAZZI_OUTPUT_DIR_PATH
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import com.github.takahirom.roborazzi.RoborazziOptions
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Test
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = RobolectricDeviceQualifiers.Pixel7Pro)
class ExampleUnitTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test() {
        composeTestRule.setContent {
            Greeting(name = "Android")
        }

        composeTestRule
            .onNode(
                hasText("Hello Android!"),
                useUnmergedTree = true
            )
            .assertExists()
    }

    @Test
    fun captureRoboImageSample() {
        composeTestRule.setContent {
            RoborazzitestTheme {
                Surface{
                    Greeting("Android")
                }
            }
        }

        composeTestRule.onRoot()
            .captureRoboImage(
                filePath = outputPath("test"),
                roborazziOptions = RoborazziOptions(compareOptions = RoborazziOptions.CompareOptions(changeThreshold = 0f))
            )
    }

    private fun outputPath(name: String) = "$DEFAULT_ROBORAZZI_OUTPUT_DIR_PATH/$name.png"
}