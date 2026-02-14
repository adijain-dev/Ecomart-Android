package com.store.stylish.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.store.stylish.Presentation.Auth.ForgotPasswordScreen
import com.store.stylish.Presentation.Auth.LoginScreen
import com.store.stylish.Presentation.Auth.SignupScreen
import com.store.stylish.Presentation.OnboardingScreen.FristInterScreen
import com.store.stylish.Presentation.OnboardingScreen.SecondInterScreen
import com.store.stylish.Presentation.OnboardingScreen.ThirdInterScreen
import com.store.stylish.Presentation.Products.ProductDetailScreen
import com.store.stylish.Presentation.Products.ProductListScreen
import com.store.stylish.Presentation.Cart.CartScreen
import com.store.stylish.Presentation.Search.SearchScreen
import com.store.stylish.Presentation.Settings.SettingsScreen
import com.store.stylish.Presentation.Splash.SplashScreen
import com.store.stylish.Presentation.UserPreferences.UserPreferencesViewModel
import com.store.stylish.Presentation.Wishlist.WishlistScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // Inject ViewModels using Hilt
    val userPreferencesViewModel: UserPreferencesViewModel = viewModel()

    // Observe user preferences state
    val userPreferencesState by userPreferencesViewModel.state.collectAsState()

    NavHost(navController = navController, startDestination = Routes.SplashScreen) {

        composable<Routes.LoginScreen> {
            LoginScreen(
               navHostController = navController
            )
        }

        composable<Routes.SignUpScreen> {
            SignupScreen(
                navHostController = navController
            )
        }

        composable<Routes.ForgetPasswordScreen> {
            ForgotPasswordScreen(navController)
        }

        composable<Routes.OnboardingScreen1> {
            FristInterScreen(navController)
        }

        composable<Routes.OnboardingScreen2> {
            SecondInterScreen(navController)
        }

        composable<Routes.OnboardingScreen3> {
            ThirdInterScreen(navController)
        }

        composable<Routes.ProductListScreen> {
            ProductListScreen(
                navController = navController
            )
        }

        composable<Routes.ProductDetailScreen> { backStackEntry ->
            val args = backStackEntry.toRoute<Routes.ProductDetailScreen>() // it convert to object
            ProductDetailScreen(
                navController = navController,
                productId = args.productId
            )
        }

        composable<Routes.SplashScreen> {
            SplashScreen(
                onFinish = {
                    val destination = when {
                        userPreferencesState.isLoggedIn -> Routes.ProductListScreen
                        userPreferencesState.isFirstTimeLogin -> Routes.OnboardingScreen1
                        else -> Routes.LoginScreen
                    }

                    navController.navigate(destination) {
                        popUpTo(Routes.SplashScreen) { inclusive = true }
                    }
                }
            )
        }

        composable<Routes.WishlistScreen> {
            WishlistScreen(
                navController = navController
            )
        }

        composable<Routes.CartScreen> {
            CartScreen(
                navController = navController
            )
        }

        composable<Routes.SearchScreen> {
            SearchScreen(
                navController = navController
            )
        }

        composable<Routes.SettingsScreen> {
            SettingsScreen(
                navController = navController
            )
        }
    }
}
