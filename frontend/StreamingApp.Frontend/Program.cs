using Microsoft.AspNetCore.Components.Web;
using Microsoft.AspNetCore.Components.WebAssembly.Hosting;
using Microsoft.AspNetCore.Components.WebAssembly.Authentication;
using StreamingApp.Frontend;

var builder = WebAssemblyHostBuilder.CreateDefault(args);
builder.RootComponents.Add<App>("#app");
builder.RootComponents.Add<HeadOutlet>("head::after");

builder.Services.AddScoped(sp => new HttpClient { BaseAddress = new Uri(builder.HostEnvironment.BaseAddress) });

builder.Services.AddOidcAuthentication(options =>
{
    builder.Configuration.Bind("Keycloak", options.ProviderOptions);
    // ? Scope required to get the refresh token
    options.ProviderOptions.DefaultScopes.Add("offline_access");  
});

var gatewayUrl = builder.Configuration["GatewayUrl"] ?? "http://localhost:8080";; // URL de tu Gateway

builder.Services.AddHttpClient("MyGateway", client => 
    client.BaseAddress = new Uri(gatewayUrl))
    .AddHttpMessageHandler(sp => 
    {
        var handler = sp.GetRequiredService<AuthorizationMessageHandler>()
            .ConfigureHandler(
                authorizedUrls: new[] { gatewayUrl }, // URLs que requieren token
                scopes: new[] { "openid", "profile", "offline_access" } // Scopes necesarios
            );
        return handler;
    });

await builder.Build().RunAsync();
